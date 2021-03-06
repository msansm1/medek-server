angular.module('medekApp.controllers').controller('ArtistController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$upload',
'ArtistService',
'ArtistTypeService',
function($scope, $rootScope, $stateParams, $location, $upload, ArtistService, ArtistTypeService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.artistId) != 'undefined') {
	    $scope.artist = [ ArtistService.artist($stateParams.artistId)
	                               .then(
			                          function(response) {
			                        	  $('.mainlist').addClass('col-md-4');
			                        	  $('.mainlist').removeClass('col-md-8');
			                              console.log("Artist : "+response.data);
			                              $scope.artist = response.data;
			                              $('.itempanel').addClass('col-md-6');
			                          }, function(reason) {
			                              alert('FAILED !!!');
			                          }) ];
    } else {
    	$scope.artist = { id: null,
						name: '',
						firstname: '',
						biolink: null,
						nationality: '',
						type: '',
						typeId: null
    	};
    	$('.mainlist').addClass('col-md-4');
    	$('.mainlist').removeClass('col-md-8');
    	$('.itempanel').addClass('col-md-6');
	}

    $scope.artists = [ ArtistService.artists()
                               .then(
		                          function(response) {
		                              console.log("Artists : "+response.data);
		                              $scope.artists = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];

    $scope.types = [ ArtistTypeService.artisttypes()
                               .then(
		                          function(response) {
		                              console.log("Artists types : "+response.data);
		                              $scope.types = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.editArtist = function(id) {
    	$location.path('/artists/edit/'+id);
        $location.replace();
    };
    
    $scope.saveArtist = function() {
    	ArtistService.saveArtist($scope.artist).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Artist saved !'});
                	$scope.$parent.getArtists();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save artist failed'});
                });
    };
    
    // picture upload
    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url: 'services/artists/'+$scope.artist.id+'/pictureupload',
            method: 'POST',
            headers: {'securtoken': $rootScope.user.token},
            //withCredentials: true,
            data: {id: $scope.artist.id},
            file: file // or list of files ($files) for html5 only
          }).progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total, 10));
          }).success(function(data, status, headers, config) {
            console.log('SUCCESS !! '+data);
          });
        }
    };

} ]);