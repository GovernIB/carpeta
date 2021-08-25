import {FunctionComponent} from 'react';
import {useColorScheme} from 'react-native';

type UseColorSchemeChildren = (isDarkMode: Boolean) => any;

interface IUseColorSchemeProps {
  children: UseColorSchemeChildren;
}

export const UseColorSchemeHook: FunctionComponent<IUseColorSchemeProps> = ({children}) => {
  const isDarkMode: Boolean = useColorScheme() === 'dark';

  return children(isDarkMode);
};
