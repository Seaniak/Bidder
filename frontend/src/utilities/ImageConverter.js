export function convertImage(image, thumbnail = false) {
  let canvas = document.createElement('canvas');
  let width = thumbnail ? 300 : 600;
  let height = thumbnail ? 300 : 600;

  canvas.width = width;
  canvas.height = height;

  let context = canvas.getContext('2d');

  context.fillStyle = 'white';
  context.fillRect(0, 0, width, height);

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
  let imageData = canvas.toDataURL('image/jpeg');

  return imageData;

  // let imageURI = this.dataUriToFile(imageData);
  // return new File([imageURI], "imageFile.png", {type: "image/png"});
}

export function dataUriToFile(dataURI) {
  // convert base64 to raw binary data held in a string
  let byteString = atob(dataURI.split(',')[1]);

  let n = byteString.length;
  // write the bytes of the string to an ArrayBuffer
  // let ab = new ArrayBuffer(n);
  let u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = byteString.charCodeAt(n);
  }
  return new File([u8arr], "imageFile.jpeg", {type: "image/jpeg"});
}