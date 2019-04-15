module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        ws: true,
        changeOrigin: true,
        secure: false
      }
    }
  },
  baseUrl: "",
  outputDir: "../src/main/resources/static"
};
