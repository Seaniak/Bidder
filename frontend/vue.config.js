module.exports = {
  devServer: {
    proxy: {
      "/assets": {
        target: "http://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      },
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
      },
      "/api/register": {
        target: "http://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/ultra-secret-socket-address": {
        target: "ws://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/api/auctions/search": {
        target: "http://localhost:8070",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/api/bids": {
        ws: true,
        target: "http://localhost:8070",
        changeOrigin: true,
        secure: false
      },
      "/api/messages": {
        ws: true,
        target: "http://localhost:8070",
        changeOrigin: true,
        secure: false
      }
    }
  },
  publicPath: "",
  outputDir: "../src/main/resources/static",
  // assetsDir: "assets"
};
