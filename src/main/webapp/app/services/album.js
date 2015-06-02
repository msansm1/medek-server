angular.module('medekApp.services').service('AlbumService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.albums = function() {
        return $http.get('services/albums/loguser/'+$rootScope.user.id);
    };
    
    this.album = function(id) {
        return $http.get('services/albums/'+id+'/loguser/'+$rootScope.user.id);
    };
    
    this.saveAlbum = function(album) {
        return $http.post('services/albums', album);
    };
    
    this.artists = function() {
    	return $http.get('services/artists/albums');
    };
    
    this.addAlbumToCollection = function(album) {
        return $http.post('services/albums/addtocollec', album);
    };
} ]);