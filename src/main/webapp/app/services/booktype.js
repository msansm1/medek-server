angular.module('medekApp.services').service('BookTypeService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.booktypes = function() {
        return $http.get('services/booktypes');
    };
    
    this.booktype = function(id) {
        return $http.get('services/booktypes/'+id);
    };
    
    this.createUpdate = function(booktype) {
        return $http.post('services/booktypes', booktype);
    };
} ]);