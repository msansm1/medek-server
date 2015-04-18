
angular.module('medekApp.controllers').controller('AlbumController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'AlbumService',
'SupportService',
'GenreService',
function($scope, $rootScope, $stateParams, $location, AlbumService, SupportService,
		GenreService) {
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

    $scope.supports = [ SupportService.supports()
                               .then(
		                          function(response) {
		                              console.log("Supports : "+response.data);
		                              $scope.supports = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];


    $scope.genres = [ GenreService.genres()
                               .then(
		                          function(response) {
		                              console.log("Genres : "+response.data);
		                              $scope.genres = response.data;
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
    
    $scope.updateAlbum = function() {
    	AlbumService.saveAlbum($scope.album);
    };

} ]);