angular.module('medekApp.services').service('ArtistService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.artists = function() {
        return $http.get('services/artists');
    };
    
    this.artist = function(id) {
        return $http.get('services/artists/'+id);
    };
    
    this.saveArtist = function(artist) {
        return $http.post('services/artists', artist);
    };
} ]);