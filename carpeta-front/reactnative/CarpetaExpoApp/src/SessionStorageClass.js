/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 02-11-2021 09:00:00
 * @modify date 02-11-2021 09:00:00
 * @desc Emulador de SessionStorage de ReactJS
 */
class SessionStorageClass {
  constructor() {
    this.data = new Map();
  }

  key(n) {
    return [...this.data.keys()][n];
  }
  getItem(key) {
    return this.data.get(key);
  }
  get length() {
    return this.data.size;
  }

  setItem(key, value) {
    this.data.set(key, value);
  }
  removeItem(key) {
    this.data.delete(key);
  }
  clear() {
    this.data = new Map();
  }
}

let sessionStorageRN = (globalThis.sessionStorageRN =
  globalThis.sessionStorageRN ?? new SessionStorageClass());

export { SessionStorageClass, sessionStorageRN };
