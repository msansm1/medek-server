angular.module('medekApp.services').service('TrackService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.tracks = function() {
        return $http.get('services/tracks');
    };
    
    this.track = function(id) {
        return $http.get('services/tracks/'+id);
    };
    
    this.saveTrack = function(track) {
        return $http.post('services/tracks', track);
    };
} ]);