function toBigPic(pos) {
    var pswpElement = document.querySelectorAll('.pswp')[0];

    var items = [];
    var getItems = function () {
        var aDiv = document.getElementById("imgs");
        if (aDiv.hasChildNodes()) {
            var imgsArr = aDiv.getElementsByTagName("img");
            for (var i = 0; i < imgsArr.length; i++) {
                var item = {
                    src: imgsArr[i].src,
                    w: imgsArr[i].naturalWidth,
                    h: imgsArr[i].naturalHeight
                };
                items.push(item);
                console.log(i + "===child====" + (item.src));
                console.log(i + "===child====" + (item.w));
                console.log(i + "===child====" + (item.h));
            }
        }
    };

    getItems();

    // define options (if needed)
    var options = {
        // history & focus options are disabled on CodePen
        history: false,
        focus: false,
        index: pos,
        showAnimationDuration: 0,
        hideAnimationDuration: 0

    };

    var gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
    gallery.init();

}