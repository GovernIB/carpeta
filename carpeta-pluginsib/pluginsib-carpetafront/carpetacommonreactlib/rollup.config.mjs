// rollup.config.js
import resolve from '@rollup/plugin-node-resolve'
import external from 'rollup-plugin-peer-deps-external'
import commonjs from '@rollup/plugin-commonjs'
import typescript from 'rollup-plugin-typescript2'
import packageJson from './package.json'  assert { type: "json" }
import json from "@rollup/plugin-json";

export default {
  input: 'src/index.ts',
  output: [
    {
      format: 'es',
      file: packageJson.module,
      exports: 'named',
      sourcemap: true
    }
  ],
  plugins: [
    resolve(),
    external(),
    commonjs({
      include: ['node_modules/**'],
    }),
    json(),
    typescript({
      clean: true,      
      exclude: ['node_modules'],
    }),
  ]
}