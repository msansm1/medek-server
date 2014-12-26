angular.module('medekApp.services').service('AlbumService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.albums = function() {
        return $http.post('services/albums', {
            id: $rootScope.user.id
        });
    };
    
    this.album = function(id) {
        return $http.post('services/albums/'+id, {
            id: $rootScope.user.id
        });
    };
} ]);