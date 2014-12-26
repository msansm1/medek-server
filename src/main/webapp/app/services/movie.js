angular.module('medekApp.services').service('MovieService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.movies = function() {
        return $http.post('services/movies', {
            id: $rootScope.user.id
        });
    };
    
    this.movie = function(id) {
        return $http.post('services/movies/'+id, {
            id: $rootScope.user.id
        });
    };
} ]);