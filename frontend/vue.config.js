module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    https: true,
    port: 8080,
    proxy: 'https://localhost:443',
    disableHostCheck: true
  },
  outputDir: '../backend/src/main/resources/dist',
}
