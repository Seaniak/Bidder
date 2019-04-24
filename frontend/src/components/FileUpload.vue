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
        previewImages: [],
        files: [],
        thumbnail: []
      }
    },
    methods: {
      upload(e) {
        this.resetArrays()
        let images = e.target.files; // array with files

        for (let image of images) {
          this.readImage(image);
        }
        this.readImage(images[0], true)

        this.$emit('uploadImage', {
          previewImages: this.previewImages,
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
              this.convertImage(image, thumbnail)
            else
              this.convertImage(image)
          }
          image.src = e.target.result
        }
      },
      convertImage(image, thumbnail = false) {
        let canvas = document.createElement('canvas');
        canvas.width = thumbnail ? 200 : 600;
        canvas.height = thumbnail ? 200 : 400;

        let context = canvas.getContext('2d');

        let imageScale = image.width / image.height;

        let resizedWidth = image.width;
        let resizedHeight = image.height;

        // only resize if image is larger than target size
        if (image.height > canvas.height ||
            image.width > canvas.width) {
          resizedWidth = canvas.width;
          resizedHeight = resizedWidth / imageScale;
          if (resizedHeight > canvas.height) {
            resizedHeight = canvas.height;
            resizedWidth = resizedHeight * imageScale;
          }
        }

        // center image in canvas
        let centerW = canvas.width / 2 - resizedWidth / 2;
        let centerH = canvas.height / 2 - resizedHeight / 2;

        context.drawImage(image,
            centerW,
            centerH,
            resizedWidth,
            resizedHeight
        );

        // convert to Base64 string for preview
        let imageData = canvas.toDataURL('image/png');

        if (thumbnail) {
          this.thumbnail.push(imageData);
          return;
        }

        this.previewImages.push(imageData);
        this.files.push(imageData);

        // let imageURI = this.dataUriToFile(imageData);
        // return new File([imageURI], "imageFile.png", {type: "image/png"});
      },
      dataUriToFile(dataURI) {
        // convert base64 to raw binary data held in a string
        let byteString = atob(dataURI.split(',')[1]);

        let n = byteString.length;
        // write the bytes of the string to an ArrayBuffer
        // let ab = new ArrayBuffer(n);
        let u8arr = new Uint8Array(n);
        while (n--) {
          u8arr[n] = byteString.charCodeAt(n);
        }
        return u8arr;
      },
      resetArrays() {
        this.files = []
        this.previewImages = []
        this.thumbnail = []
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