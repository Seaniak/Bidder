<template>
  <div id="upload">
    <input id="file-load" type="file" @change="upload" />
    <label for="file-load">
      <v-icon dark large>
        add_photo_alternate
      </v-icon>
    </label>
  </div>
</template>

<script>
  export default {
    name: "FileUpload",
    methods: {
      upload(e) {
        let images = e.target.files; // array with files

          this.$emit('uploadImage', images)

        // let reader = new FileReader();
        // reader.readAsDataURL(image);  // read file to this format
        // reader.onload = (r) => { // when file is loaded
        //   let imageData = r.target.result;
        // }
      }
    }
  };
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
  color: #42b983;
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
