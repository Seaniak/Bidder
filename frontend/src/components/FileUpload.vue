<template>
  <div id="upload">
    <input
            id="file-load"
            type="file"
            accept="image/*"
            multiple
            required
            @change="upload"/>
    <label for="file-load">
      <v-icon dark>cloud_upload</v-icon>
    </label>
    <div v-if="files[0]">
      <img v-for="(image, i) of files"
           width="20%"
           :src="image"
           :key="i"
           alt="profile picture"
           :class="{ 'active': activeIndex === i }"
           @click="readThumbnail(i)">
    </div>
  </div>
</template>

<script>
  import {convertImage} from "@/utilities/ImageConverter";

  export default {
    name: "FileUpload",
    data() {
      return {
        images: [],
        files: [],
        thumbnail: [],
        activeIndex: 0
      }
    },
    methods: {
      upload(e) {
        this.resetArrays()
        this.images = e.target.files; // array with files

        for (let image of this.images) {
          this.readImage(image);
        }
        this.readThumbnail(0)
      },
      readThumbnail(index) {
        this.activeIndex = index;

        this.readImage(this.images[index], true)

        this.$emit('uploadImage', {
          files: this.files,
          thumbnail: this.thumbnail
        })
      },
      readImage(imageFile, thumbnail = false) {
        let reader = new FileReader();
        reader.readAsDataURL(imageFile);  // read file to this format
        reader.onload = e => { // when file is loaded
          let image = new Image();
          image.onload = () => {
            if (thumbnail)
              this.thumbnail[0] = (convertImage(image, true))
            else
              this.files.push(convertImage(image))
          }
          image.src = e.target.result
        }
      },
      resetArrays() {
        this.files = []
        this.images = []
        this.thumbnail = []
      }
    }
  }
</script>

<style scoped>
  .active{
    border: 2px solid greenyellow;
  }
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