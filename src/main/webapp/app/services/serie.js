angular.module('medekApp.services').service('SerieService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.series = function() {
        return $http.get('services/tvshows', {
            id: $rootScope.user.id
        });
    };
    
    this.serie = function(id) {
        return $http.get('services/tvshows/'+id);
    };
    
    this.saveSerie = function(serie) {
        return $http.post('services/tvshows', serie);
    };
} ]);