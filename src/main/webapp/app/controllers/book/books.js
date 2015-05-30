
angular.module('medekApp.controllers').controller('BooksController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'BookService',
function($scope, $rootScope, $stateParams, $location, BookService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getBooks = function() {
    	BookService.books().then(
           function(response) {
               console.log("Books : "+response.data);
               $scope.books = response.data;
           }, function(reason) {
               alert('FAILED !!!');
           });
    };
    
    $scope.books = [ $scope.getBooks() ];
    
    $scope.openBook = function(id) {
    	$location.path('/books/'+id);
        $location.replace();
    }
    
    $scope.createBook = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);