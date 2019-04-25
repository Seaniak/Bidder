module.exports = {
  resolve: {
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      '@/': resolve('src/'),
    },
    extensions: ['*', '.js', '.vue', '.json']
  },
}
