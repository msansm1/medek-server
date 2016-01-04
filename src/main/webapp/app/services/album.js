angular.module('medekApp.services').service('AlbumService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.albums = function() {
        return $http.get('services/albums/loguser/'+$rootScope.user.id);
    };

    this.albumlist = function(from, limit, orderBy, orderDir) {
        return $http.get('services/albums?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir);
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

    this.userAlbums = function(userId) {
        return $http.get('services/albums/user/'+userId);
    };
} ]);