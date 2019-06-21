$(document).ready(function () {
    getMovieOn();

});
function getMovieOn() {
    getRequest(
        '/home/movie',
        function (res) {
            if (res.success) {
                renderMovie(res.content);
            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

function renderMovie(movies) {
    $('.movie-on-list').empty();
    var movieDomStr="";
    movies.forEach(function (movie) {
        movieDomStr+=
            '<div class="list-item">'+
            '<a href="/user/movieDetail?id='+movie.id+'"><img class="movie-post" src="'+movie.posterUrl+'" ></a>'+
            '<p class="movie-name">'+movie.name+'</p>'+
            '</div>';
    });
    $('.movie-on-list').append(movieDomStr);
}