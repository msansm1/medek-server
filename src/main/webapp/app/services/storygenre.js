angular.module('medekApp.services').service('StoryGenreService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.storygenres = function() {
        return $http.get('services/storygenres');
    };
    
    this.storygenre = function(id) {
        return $http.get('services/storygenres/'+id);
    };
} ]);