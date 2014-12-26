angular.module('medekApp.services').service('BookService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.books = function() {
        return $http.post('services/books', {
            id: $rootScope.user.id
        });
    };
    
    this.book = function(id) {
        return $http.post('services/books/'+id, {
            id: $rootScope.user.id
        });
    };
} ]);