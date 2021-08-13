module.exports = {
  root: true,
  extends: '@react-native-community',
  plugins: ['react'],
  rules: {
    'react/jsx-uses-react': 'error',
    'react/jsx-uses-vars': 'error',
    'prettier/prettier': ['error', {endOfLine: 'auto'}],
    'max-len': ['error', 120, 2], // ["error", max-len, tab-witdh] (i use 2, default is 4)
  },
};
