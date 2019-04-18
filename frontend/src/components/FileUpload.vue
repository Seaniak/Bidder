<template>
  <div id="upload">
    <input id="file-load" type="file" @change="upload"/>
    <label for="file-load">
      <v-icon
              dark
              large>
        add_photo_alternate
      </v-icon>
    </label>
  </div>
</template>

<script>
  export default {
    name: "FileUpload",
    data() {
      return {
        files: []
      }
    },
    methods: {
      upload(e) {
        let images = e.target.files; // array with files

        for (let image of images) {
          this.readImage(image)
        }
        this.$emit('uploadImage', this.files)
      },
      readImage(imageFile) {
        let reader = new FileReader();
        reader.readAsDataURL(imageFile);  // read file to this format
        reader.onload = e => { // when file is loaded

          let image = new Image();
          image.onload = () => {
            this.convertImage(image)
          }
        }
      },
      convertImage(image) {
        let canvas = document.createElement('canvas');
        canvas.width = 800;
        canvas.height = 600;

        let context = canvas.getContext('2d');

        context.drawImage(image,
            canvas.width / 2 - image.width / 2,
            canvas.height / 2 - image.height / 2
        );

        image.src = canvas.toDataURL('image/png');

        this.files.push(image);
      }
    }
  }
</script>

<style scoped>
  #upload {
    display: flex;
  }

  #file-load {
    width: 0.1px;
    height: 0.1px;
  }

  label {
    color: #42B983;
    margin-right: auto;
    font-size: 44px;
    display: flex;
    justify-content: center;
    width: 56px;
    height: 56px;
    border-radius: 50px;
    background-color: teal;
  }
</style>