$(document).ready(function () {
    renderCarouselMovie([1,2,3,4]);
    //getCarouselMovie();

});
function getCarouselMovie() {
    getRequest(
        '',
        function (res) {
            if (res.success) {
                renderCarouselMovie(res.content);
            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

// function renderCarouselMovie(movies) {
//     $('.movie-on-list').empty();
//     var movieDomStr="";
//     movies.forEach(function (movie) {
//         movieDomStr+=
//             '<div class="list-item">'+
//             '<img class="movie-post" src="http://n.sinaimg.cn/translate/640/w600h840/20190312/ampL-hufnxfm4278816.jpg" >'+
//             '<p class="movie-name">夏目友人帐</p>'+
//             '</div>';
//     });
//     $('.movie-on-list').append(movieDomStr);
// }
function renderCarouselMovie(movies) {
    $('#carousel').empty();
    var movieDomStr="";
    movies.forEach(function (movie) {
        movieDomStr+='<img src="../images/1.jpeg" data-url="/user/movieDetail?id=10">';
    });
    $('#carousel').append(movieDomStr);
}