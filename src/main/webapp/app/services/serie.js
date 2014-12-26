angular.module('medekApp.services').service('SerieService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.series = function() {
        return $http.post('services/series', {
            id: $rootScope.user.id
        });
    };
    
    this.serie = function(id) {
        return $http.post('services/series/'+id, {
            id: $rootScope.user.id
        });
    };
} ]);