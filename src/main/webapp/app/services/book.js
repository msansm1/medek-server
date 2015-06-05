angular.module('medekApp.services').service('BookService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.books = function() {
        return $http.get('services/books/loguser/'+$rootScope.user.id);
    };
    
    this.book = function(id) {
        return $http.get('services/books/'+id+'/loguser/'+$rootScope.user.id);
    };
    
    this.saveBook = function(book) {
        return $http.post('services/books', book);
    };
    
    this.artists = function() {
    	return $http.get('services/artists/books');
    };
    
    this.addBookToCollection = function(book) {
        return $http.post('services/books/addtocollec', book);
    };
} ]);