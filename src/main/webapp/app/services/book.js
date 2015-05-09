angular.module('medekApp.services').service('BookService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.books = function() {
        return $http.get('services/books');
    };
    
    this.book = function(id) {
        return $http.get('services/books/'+id);
    };
    
    this.saveBook = function(book) {
        return $http.post('services/books', book);
    };
    
    this.artists = function() {
    	return $http.get('services/artists/books');
    };
} ]);