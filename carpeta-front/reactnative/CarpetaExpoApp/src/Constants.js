/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 15:16:49
 * @modify date 18-08-2021 08:35:37
 * @desc Constant de Color i Dimensions de la Pantalla
 */
import { Dimensions } from "react-native";

/** Color negre. */
export const Color_BLACK = "#000000";

/**  Color blanc */
export const Color_WHITE = "#FFFFFF";

const tintColorLight = "#2f95dc";
const tintColorDark = "#fff";

const width = Dimensions.get("window").width;
const height = Dimensions.get("window").height;

export default {
  light: {
    text: "#000",
    background: "#fff",
    tint: tintColorLight,
    tabIconDefault: "#ccc",
    tabIconSelected: tintColorLight,
  },
  dark: {
    text: "#fff",
    background: "#000",
    tint: tintColorDark,
    tabIconDefault: "#ccc",
    tabIconSelected: tintColorDark,
  },
  window: {
    width,
    height,
  },
  isSmallDevice: width < 375,
};
