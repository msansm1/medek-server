
angular.module('medekApp.controllers').controller('AlbumController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'AlbumService',
function($scope, $rootScope, $stateParams, $location, AlbumService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.album = [ AlbumService.album($stateParams.albumId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("Album : "+response.data);
		                              $scope.album = response.data;
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.editAlbum = function(id) {
    	$location.path('/albums/edit/'+id);
        $location.replace();
    }

    // for date picker
    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope.opened = true;
    };
    
    $scope.update = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);