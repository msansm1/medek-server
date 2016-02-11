angular.module('medekApp.services').service('GenreService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.genres = function() {
        return $http.get('services/genres');
    };
    
    this.genre = function(id) {
        return $http.get('services/genres/'+id);
    };
    
    this.createUpdate = function(genre) {
        return $http.post('services/genres', genre);
    };
} ]);