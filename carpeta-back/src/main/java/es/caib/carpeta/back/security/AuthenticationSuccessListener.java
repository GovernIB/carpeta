package es.caib.carpeta.back.security;

import es.caib.carpeta.back.preparer.BasePreparer;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.EntidadService;
import es.caib.carpeta.ejb.UsuarioEntidadService;
import es.caib.carpeta.ejb.UsuarioService;
import es.caib.carpeta.ejb.utils.EjbManager;
import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.Usuario;
import es.caib.carpeta.persistence.UsuarioEntidad;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author anadal(u80067)
 *
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	protected final Logger log = Logger.getLogger(getClass());

	/*
	 * public static final Set<String> allowedApplicationContexts = new
	 * HashSet<String>();
	 */

	@Override
	public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication au = sc.getAuthentication();

		if (au == null) {
			// TODO traduccio
			throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");
		}

		User user = (User) au.getPrincipal();

		String username = user.getUsername();
		log.debug(" =================================================================");
		log.info(" ============ Login Usuari: " + username);

		try {
			LoginInfo loginInfo = LoginInfo.getInstance();

			if (!username.equals(loginInfo.getUsuariPersona().getUsername())) {
				throw new LoginException("Amb aquest navegador ja s'ha autenticat amb un altre usuari."
						+ " Tanqui el navegador completament.");
			}
		} catch (Throwable e) {
			log.info(" XYZ ZZZ ZZZ S'ha produit un error consultant la informació de login actual: " + e.getMessage());
		}

		final boolean isDebug = log.isDebugEnabled();

		// Cercam si té el ROLE_ADMIN o ROLE_SUPER
		Collection<GrantedAuthority> seyconAuthorities = user.getAuthorities();
		boolean containsRoleAdEn = false;
		boolean containsRoleSuper = false;

		if (seyconAuthorities.size() == 0) {
			log.info(" =======  XYZ ZZZ NO TE CAP ROL =========== ");
		} else {

			for (GrantedAuthority grantedAuthority : seyconAuthorities) {
				String rol = grantedAuthority.getAuthority();

				log.info("XYZ ZZZ ZZZ Rol SEYCON : " + rol);

				if (Constants.ROLE_ADMIN.equals(rol)) {
					containsRoleAdEn = true;
				}
				if (Constants.ROLE_SUPER.equals(rol)) {
					containsRoleSuper = true;
				}
			}
		}

		UsuarioEntidadService usuariEntitatLogicaEjb;
		UsuarioService usuariPersonaLogicaEjb;
		EntidadService entitatLogicaEjb;

		try {
			usuariEntitatLogicaEjb = EjbManager.getUsuariEntitatLogicaEJB();
			usuariPersonaLogicaEjb = EjbManager.getUsuariPersonaLogicaEJB();
			entitatLogicaEjb = EjbManager.getEntitatLogicaEJB();
		} catch (I18NException e) {
			// TODO traduccio
			throw new LoginException("No puc accedir al gestor d´obtenció de" + " informació d´usuari-entitat per "
					+ username + ": " + e.getMessage(), e);
		}

		Usuario usuariPersona = usuariPersonaLogicaEjb.findByUsername(username);
		boolean necesitaConfigurar = false;

		if (usuariPersona == null) {
			// Revisar si és un Administrador que entra per primera vegada

			{
				try {
					Usuario persona;
					// XYZ ZZZ ZZZ ZZZ TODO
					persona = usuariPersonaLogicaEjb.getUserInfoFromUserInformation(username);

					if (persona != null) {

						UsuarioEntidad usuariEntitat = null;

						if (containsRoleAdEn) {
							// XYZ ZZZ ZZZ
							Long defaultEntity = null;

							if (Configuracio.isCAIB()) {
								defaultEntity = Configuracio.getDefaultEntity();
							} else {
								defaultEntity = null;
							}

							if (defaultEntity != null) {
								usuariEntitat = new UsuarioEntidad();
								// usuariEntitat.setActiu(true);
								usuariEntitat.setEntidad(entitatLogicaEjb.getReference(defaultEntity));

								// XYZ ZZZ
								// usuariEntitat.setPredeterminat(true);
								usuariEntitat.setUsuario(persona);
							}

						}

						necesitaConfigurar = true;

						if (usuariEntitat == null) {
							usuariPersona = usuariPersonaLogicaEjb.crearUsuario(persona);
						} else {
							usuariEntitat = usuariEntitatLogicaEjb.create(usuariEntitat);
							usuariPersona = usuariEntitat.getUsuario();
						}

						if (isDebug) {
							log.debug("necesitaConfigurarUsuari = " + necesitaConfigurar);
						}

					}

				} catch (Throwable e) {
					usuariPersona = null;
					String msg;
					if (e instanceof I18NException) {
						msg = I18NUtils.getMessage((I18NException) e);
					} else if (e instanceof I18NValidationException) {
						msg = I18NUtils.getMessage((I18NValidationException) e);
					} else {
						msg = e.getMessage();
					}

					log.error("Error llegint informació del plugin de User Information: " + msg, e);
				}
			}

		}

		Set<UsuarioEntidad> usuariEntitats = null;
		if (usuariPersona != null) {
			// usuariEntitats = usuariPersona.getUsuariEntitats();

			usuariEntitats = new HashSet<UsuarioEntidad>(
					usuariEntitatLogicaEjb.findAllByPersonaId(usuariPersona.getId()));

		}

		if (usuariEntitats == null) {
			usuariEntitats = new HashSet<UsuarioEntidad>();
		}

		if (!containsRoleAdEn && usuariEntitats.size() != 0) {
			// L'usuari " + name + " està assignat a una o varies
			// entitats però no té el rol ROLE_ADEN";
			I18NTranslation translation = new I18NTranslation("error.sensepfiuser", username);
			log.error("");
			log.error(I18NUtils.tradueix(translation));
			log.error("Authenntication Info:\n" + au);
			log.error("");

			// Com enviar-ho a la PAGINA WEB
			BasePreparer.loginErrorMessage.put(username, translation);

			usuariEntitats = new HashSet<UsuarioEntidad>();
		}

		// Seleccionam l'entitat per defecte i verificam que les entitats disponibles
		// siguin correctes
		Map<Long, Entidad> entitats = new HashMap<Long, Entidad>();
		Map<Long, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<Long, Set<GrantedAuthority>>();
		rolesPerEntitat.put((Long) null, new HashSet<GrantedAuthority>(seyconAuthorities));
		Map<Long, UsuarioEntidad> usuariEntitatPerEntitatID = new HashMap<Long, UsuarioEntidad>();
		Entidad entitatPredeterminada = null;
		for (UsuarioEntidad usuariEntitat : usuariEntitats) {

			Entidad entitat = usuariEntitat.getEntidad();

			if (entitat == null) {
				// XYZ ZZZ ZZZ
//        log.info("Configuracio.getDefaultEntity() = ]" + PropietatGlobalUtil.getDefaultEntity() + "[");
//        log.info("ROLES = ]" + Arrays.toString(seyconAuthorities.toArray())+ "[");
//        log.warn("Entitat val null");
				continue;
			}

			Long entitatID = entitat.getId();
			if (isDebug) {
				log.debug("--------------- Entitat " + entitatID);
			}
			// Check deshabilitada
			// XYZ ZZZ ZZZ
			/*
			 * if (!entitat.isActiva()) { log.warn("L'entitat " + entitat.getNom() +
			 * " esta deshabilitada."); continue; } if (!usuariEntitat.isActiu()) {
			 * log.warn("L'entitat " + entitat.getNom() +
			 * " esta deshabilitatda per l'usuari " + usuariPersona.getId()); continue; }
			 */

			// Per si no n'hi ha cap per defecte
			if (entitatPredeterminada == null) {
				entitatPredeterminada = entitat;
			}
			// Cercam Rols Virtuals ROLE_ADMIN de carpeta
			if (containsRoleAdEn) {

				Set<GrantedAuthority> rolescarpeta = new TreeSet<GrantedAuthority>(GRANTEDAUTHORITYCOMPARATOR);
				rolescarpeta.addAll(seyconAuthorities);

				// XYZ ZZZ ZZZ
				/*
				 * if (usuariEntitat.isAden()) { rolescarpeta.add(new
				 * SimpleGrantedAuthority(Constants.CAR_ADEN)); }
				 */

				rolesPerEntitat.put(entitatID, rolescarpeta);
			} else {
				log.debug("No te el role seycon CAR_ADMIN");
				rolesPerEntitat.put(entitatID, new HashSet<GrantedAuthority>());
			}
			// Entitat per defecte
			// XYZ ZZZ ZZZ
//      if (usuariEntitat.isPredeterminat()) {
//        entitatPredeterminada = entitat;
//      }

			// Entitats
			entitats.put(entitatID, usuariEntitat.getEntidad());
			// Usuari Entitat
			usuariEntitat.setUsuario(usuariPersona);
			usuariEntitatPerEntitatID.put(entitatID, usuariEntitat);
		}

		log.info(" XYZ ZZZ  entitats.size()  => " + entitats.size());
		log.info(" XYZ ZZZ  containsRoleSuper  => " + containsRoleSuper);
		log.info(" XYZ ZZZ  !containsRoleSuper => " + !containsRoleSuper);

		if (entitats.size() == 0 && containsRoleAdEn) {

			if (usuariEntitats.size() == 0) {
				// L'usuari " + name + " no té cap entitat associada. Consulti amb
				// l'Administrador
				I18NTranslation translation = new I18NTranslation("error.senseentitat", username);
				BasePreparer.loginErrorMessage.put(username, translation);
			} else {
				// Les entitats a les que pertany estan desactivades
				// "L'usuari " + name + " no té cap entitat vàlida associada");
				I18NTranslation translation = new I18NTranslation("error.senseentitatvalida", username);
				BasePreparer.loginErrorMessage.put(username, translation);
			}
		}

		Long entitatIDActual = null;
		if (entitatPredeterminada != null) {
			entitatIDActual = entitatPredeterminada.getId();
			if (isDebug) {
				log.debug(">>>>>> Entitat predeterminada " + entitatIDActual);
			}
		}

		LoginInfo loginInfo;
		// create a new authentication token

		log.info("user => " + user);
		log.info("usuariPersona => " + usuariPersona);
		log.info("entitatIDActual => " + entitatIDActual);
		log.info(" entitats => " + entitats);
		log.info("rolesPerEntitat  => " + rolesPerEntitat);
		log.info(" usuariEntitatPerEntitatID => " + usuariEntitatPerEntitatID);
		log.info("necesitaConfigurar => " + necesitaConfigurar);

		loginInfo = new LoginInfo(username, user, usuariPersona, entitatIDActual, entitats, rolesPerEntitat,
				usuariEntitatPerEntitatID, necesitaConfigurar);

		// and set the authentication of the current Session context
		SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

		if (isDebug) {
			log.debug(">>>>>> Final del Process d'autenticació.");
		}
		log.debug(" =================================================================");

	}

	public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
		@Override
		public int compare(GrantedAuthority o1, GrantedAuthority o2) {
			return -o1.getAuthority().compareTo(o2.getAuthority());
		}
	};

}
