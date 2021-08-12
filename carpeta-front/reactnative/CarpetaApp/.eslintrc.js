module.exports = {
  root: true,
  extends: '@react-native-community',
  plugins: ['react'],
  rules: {
    'react/jsx-uses-react': 'error',
    'react/jsx-uses-vars': 'error',
    'prettier/prettier': ['error', {endOfLine: 'auto'}],
  },
};
