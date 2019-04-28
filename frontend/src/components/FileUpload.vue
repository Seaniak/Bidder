<template>
  <div id="upload" class="row">
    <div class="mb-3">
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
    </div>
    <div v-show="previewImages[0]" id="image-files">
      <img v-for="(image, i) of previewImages"
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
        previewImages: [],
        previewThumbnail: [],
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
        this.thumbnail = []

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
            if (thumbnail) {
              this.dataUriToFile(convertImage(image, true), true)
            } else {
              let convertedImage = convertImage(image);
              this.previewImages.push(convertedImage)
              this.dataUriToFile(convertedImage)
            }
          }
          image.src = e.target.result
        }
      },
      dataUriToFile(dataURI, thumb = false) {
        // convert base64 to raw binary data held in a string
        let byteString = atob(dataURI.split(',')[1]);

        let n = byteString.length;
        // write the bytes of the string to an ArrayBuffer
        // let ab = new ArrayBuffer(n);
        let u8arr = new Uint8Array(n);
        while (n--) {
          u8arr[n] = byteString.charCodeAt(n);
        }
        let file = new File([u8arr], "imageFile.jpeg", {type: "image/jpeg"});

        if (thumb)
          this.thumbnail.push(file)
        else
          this.files.push(file)
      },
      resetArrays() {
        this.files = []
        this.images = []
        this.thumbnail = []
        this.previewImages = []
      }
    }
  }
</script>

<style scoped>
  .active {
    border: 2px solid greenyellow;
  }

  #image-files {
    width: 100%;
  }

  img {
    margin-right: 1%;
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