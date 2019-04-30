export default class Swipe {
  constructor(element) {
    this.xDown = null;
    this.yDown = null;
    this.element = element;

    this.element.addEventListener("touchstart", e => {
      this.xDown = e.touches[0].clientX;
      this.yDown = e.touches[0].clientY;
    });
  }
  handleSwipe(e) {
    e.preventDefault();

    if (!this.xDown || !this.yDown) {
      return;
    }

    let xUp = e.touches[0].clientX;
    let yUp = e.touches[0].clientY;

    this.xDiff = this.xDown - xUp;
    this.yDiff = this.yDown - yUp;

    if (Math.abs(this.xDiff) > Math.abs(this.yDiff)) {
      if (this.xDiff > 20) {
        this.onLeft();
      } else {
        this.onRight();
      }
    } else {
      if (this.yDiff > 20) {
        this.onUp();
      } else {
        this.onDown();
      }
    }

    this.xDown = null;
    this.yDown = null;
  }
  run() {
    this.element.addEventListener("touchmove", e => {
      this.handleSwipe(e);
    });
  }

  onLeft(callback) {
    this.onLeft = callback;
    return this;
  }

  onRight(callback) {
    this.onRight = callback;
    return this;
  }

  onUp(callback) {
    this.onUp = callback;
    return this;
  }

  onDown(callback) {
    this.onDown = callback;
    return this;
  }
}
