
angular.module('medekApp.controllers').controller('MoviesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'MovieService',
function($scope, $rootScope, $stateParams, $location, MovieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.gridMovies = { 
        enableSorting: true,
        enableFiltering: true,
        enableRowSelection: true,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        multiSelect: false,
        columnDefs: [ { field: 'title', displayName: 'title', headerCellFilter: 'translate'},
                      { field: 'year', displayName: 'year', headerCellFilter: 'translate'},
                      { field: 'realisator', displayName: 'realisator', headerCellFilter: 'translate'}],
        onRegisterApi: function( gridApi ) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                var msg = 'movie ID selected : ' + row.entity.id;
                console.log(msg);
//                $location.path('/movies/'+row.entity.id);
//                $location.replace();
            });
        }
    };
    
    $scope.gridMovies.data = [ MovieService.movies()
                               .then(
		                          function(response) {
		                              console.log("Movies : "+response.data);
		                              $scope.gridMovies.data = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.createMovie = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);