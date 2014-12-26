
angular.module('medekApp.controllers').controller('AlbumsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'AlbumService',
function($scope, $rootScope, $stateParams, $location, AlbumService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.gridAlbums = { 
        enableSorting: true,
        enableFiltering: true,
        enableRowSelection: true,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        multiSelect: false,
        columnDefs: [ { field: 'title', displayName: 'title', headerCellFilter: 'translate'},
                      { field: 'artist', displayName: 'artist', headerCellFilter: 'translate'},
                      { field: 'year', displayName: 'year', headerCellFilter: 'translate'}],
        onRegisterApi: function( gridApi ) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                var msg = 'album ID selected : ' + row.entity.id;
                console.log(msg);
//                $location.path('/albums/'+row.entity.id);
//                $location.replace();
            });
        }
    };
    
    $scope.gridAlbums.data = [ AlbumService.albums()
                               .then(
		                          function(response) {
		                              console.log("Albums : "+response.data);
		                              $scope.gridAlbums.data = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.createAlbum = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);