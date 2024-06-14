// 이미지가 변경될 때마다 갤러리 업데이트
$('#product_image').on('change', function() {
    let galleryContainer = $('.gallery');
    galleryContainer.empty(); // 기존 이미지 삭제

    var files = this.files;
    $.each(files, function(index, file) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('<img>').attr('src', e.target.result).appendTo(galleryContainer);
        }

        reader.readAsDataURL(file);
    });
});


// 이미지 갤러리에 슬라이더 추가 (예: Slick을 사용하는 경우)
$('.gallery').slick({
    dots: true,
    infinite: true,
    speed: 300,
    slidesToShow: 1,
    adaptiveHeight: true
});