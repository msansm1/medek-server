angular.module('medekApp.services').service('AlbumService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.albumlist = function(from, limit, orderBy, orderDir) {
        return $http.get('services/albums?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir);
    };

    this.userAlbumlist = function(from, limit, orderBy, orderDir, userId) {
        return $http.get('services/albums/user?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir+'&userId='+userId);
    };
    
    this.album = function(id) {
        return $http.get('services/albums/'+id);
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