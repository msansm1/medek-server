
angular.module('medekApp.controllers').controller('BooksController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'BookService',
function($scope, $rootScope, $stateParams, $location, BookService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.gridBooks = { 
        enableSorting: true,
        enableFiltering: true,
        enableRowSelection: true,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        multiSelect: false,
        columnDefs: [ { field: 'title', displayName: 'title', headerCellFilter: 'translate'},
                      { field: 'author', displayName: 'author', headerCellFilter: 'translate'},
                      { field: 'genre', displayName: 'genre', headerCellFilter: 'translate'}],
        onRegisterApi: function( gridApi ) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                var msg = 'book ID selected : ' + row.entity.id;
                console.log(msg);
//                $location.path('/books/'+row.entity.id);
//                $location.replace();
            });
        }
    };
    
    $scope.gridBooks.data = [ BookService.books()
                               .then(
		                          function(response) {
		                              console.log("Books : "+response.data);
		                              $scope.gridBooks.data = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.createBook = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);