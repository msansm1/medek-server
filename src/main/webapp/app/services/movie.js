angular.module('medekApp.services').service('MovieService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.movies = function() {
        return $http.get('services/movies/loguser/'+$rootScope.user.id);
    };
    
    this.movie = function(id) {
        return $http.get('services/movies/'+id+'/loguser/'+$rootScope.user.id);
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