
angular.module('medekApp.controllers').controller('SeriesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'SerieService',
function($scope, $rootScope, $stateParams, $location, SerieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.gridSeries = { 
        enableSorting: true,
        enableFiltering: true,
        enableRowSelection: true,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        multiSelect: false,
        columnDefs: [ { field: 'title', displayName: 'title', headerCellFilter: 'translate'},
                      { field: 'season', displayName: 'season', headerCellFilter: 'translate'},
                      { field: 'year', displayName: 'year', headerCellFilter: 'translate'}],
        onRegisterApi: function( gridApi ) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                var msg = 'serie ID selected : ' + row.entity.id;
                console.log(msg);
//                $location.path('/series/'+row.entity.id);
//                $location.replace();
            });
        }
    };
    
    $scope.gridSeries.data = [ SerieService.series()
                               .then(
		                          function(response) {
		                              console.log("Series : "+response.data);
		                              $scope.gridSeries.data = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.createSerie = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);