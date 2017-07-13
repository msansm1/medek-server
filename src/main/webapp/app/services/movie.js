angular.module('medekApp.services').service('MovieService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.movielist = function(from, limit, orderBy, orderDir) {
        return $http.get('services/movies?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir);
    };

    this.userMovielist = function(from, limit, orderBy, orderDir, userId) {
        return $http.get('services/movies/user?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir+'&userId='+userId);
    };
    
    this.movie = function(id) {
        return $http.get('services/movies/'+id);
    };
    
    this.saveMovie = function(movie) {
        return $http.post('services/movies', movie);
    };
    
    this.artists = function() {
    	return $http.get('services/artists/movies');
    };
    
    this.addMovieToCollection = function(movie) {
        return $http.post('services/movies/addtocollec', movie);
    };
} ]);