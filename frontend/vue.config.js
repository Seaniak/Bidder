module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/login": {
        target: "http://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/logout": {
        target: "http://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      }
    }
  },
  baseUrl: "",
  outputDir: "../src/main/resources/static"
};
