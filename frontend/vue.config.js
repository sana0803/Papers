module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    port: 8080,
    proxy: 'http://localhost:80',
    disableHostCheck: true
  },
  outputDir: '../backend/src/main/resources/dist',
}
