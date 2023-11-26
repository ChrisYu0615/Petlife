
$('.slider-for').slick({
  infinite: true,
  slidesToShow: 1,
  slidesToScroll: 3,
  arrows: false,
  fade: true,
  asNavFor: '.slider-nav'
});
$('.slider-nav').slick({
  infinite: true,
  slidesToShow: 4,
  slidesToScroll: 4,
  asNavFor: '.slider-for',
  dots: false,
  focusOnSelect: true
});