angular.module('medekApp.services').service('AlbumService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.albums = function() {
        return $http.get('services/albums');
    };
    
    this.album = function(id) {
        return $http.get('services/albums/'+id);
    };
    
    this.saveAlbum = function(album) {
        return $http.post('services/albums', album);
    };
} ]);