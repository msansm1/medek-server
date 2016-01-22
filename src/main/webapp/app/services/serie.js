angular.module('medekApp.services').service('SerieService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.series = function() {
        return $http.get('services/tvshows/loguser/'+$rootScope.user.id);
    };

    this.serieslist = function(from, limit, orderBy, orderDir) {
        return $http.get('services/tvshows?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir);
    };
    
    this.serie = function(id) {
        return $http.get('services/tvshows/'+id+'/loguser/'+$rootScope.user.id);
    };
    
    this.saveSerie = function(serie) {
        return $http.post('services/tvshows', serie);
    };
    
    this.artists = function() {
    	return $http.get('services/artists/series');
    };
    
    this.addSerieToCollection = function(serie) {
        return $http.post('services/tvshows/addtocollec', serie);
    };

    this.userSerieslist = function(from, limit, orderBy, orderDir, userId) {
        return $http.get('services/tvshows/user?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir+'&userId='+userId);
    };
} ]);