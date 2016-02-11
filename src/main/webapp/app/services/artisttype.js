angular.module('medekApp.services').service('ArtistTypeService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.artisttypes = function() {
        return $http.get('services/artisttypes');
    };
    
    this.artisttype = function(id) {
        return $http.get('services/artisttypes/'+id);
    };
    
    this.createUpdate = function(artisttype) {
        return $http.post('services/artisttypes', artisttype);
    };
} ]);