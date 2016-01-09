
angular.module('medekApp.controllers').controller('BookController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'$upload',
'BookService',
'BookTypeService',
'CollectionService',
'EditorService',
'LangService',
'StoryGenreService',
function($scope, $rootScope, $stateParams, $location, $modal, $upload, BookService, BookTypeService,
		CollectionService, EditorService, LangService, StoryGenreService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.bookId) != 'undefined') {
        $scope.book = [ BookService.book($stateParams.bookId)
                        .then(
	                          function(response) {
	                        	  $('.mainlist').addClass('col-md-4');
	                        	  $('.mainlist').removeClass('col-md-8');
	                              console.log("Book : "+response.data);
	                              $scope.book = response.data;
	                              if ($location.path().indexOf('/me/') > -1) {
	                           	   $scope.book.myview = true;
	                              } else {
	                           	   $scope.book.myview = false;
	                              }
	                              $('.itempanel').addClass('col-md-6');
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    } else {
    	$scope.book = { id: null,
    					title: '',
    					author: '',
    					authorId: null,
    					editor: '',
    					editorId: null,
    					collection: '',
    					collectionId: null,
    					cover: '',
    					description: '',
    					publicationDate: null,
    					genre: '',
    					genreId: null,
    					type: '',
    					typeId: null,
    					lang: '',
    					langId: null,
    					series: '',
    					bookNb: null,
    					isSerieDone: false,
    	};
  	    $('.mainlist').addClass('col-md-4');
	    $('.mainlist').removeClass('col-md-8');
        $('.itempanel').addClass('col-md-6');
    }
    
    $scope.types = [ BookTypeService.booktypes()
                        .then(
	                          function(response) {
	                              console.log("Book types : "+response.data);
	                              $scope.types = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.collections = [ CollectionService.collections()
                        .then(
	                          function(response) {
	                              console.log("Collections : "+response.data);
	                              $scope.collections = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.editors = [ EditorService.editors()
                        .then(
	                          function(response) {
	                              console.log("Editors : "+response.data);
	                              $scope.editors = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.langs = [ LangService.langs()
                        .then(
	                          function(response) {
	                              console.log("Langs : "+response.data);
	                              $scope.langs = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.genres = [ StoryGenreService.storygenres()
                        .then(
	                          function(response) {
	                              console.log("Story genres : "+response.data);
	                              $scope.genres = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

    $scope.authors = [ BookService.artists()
                               .then(
		                          function(response) {
		                              console.log("authors : "+response.data);
		                              $scope.authors = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
    
    $scope.editBook = function(id) {
    	$location.path('/books/me/edit/'+id);
        $location.replace();
    };
    
    $scope.saveBook = function() {
    	BookService.saveBook($scope.book).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Book saved !'});
                	$scope.$parent.getBooks();
                	$location.path('/books/edit/'+response.data.id);
                    $location.replace();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save book failed'});
                });
    };
    
    // cover upload
    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url: 'services/books/'+$scope.book.id+'/coverupload',
            method: 'POST',
            headers: {'securtoken': $rootScope.user.token},
            //withCredentials: true,
            data: {id: $scope.book.id},
            file: file // or list of files ($files) for html5 only
          }).progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total, 10));
          }).success(function(data, status, headers, config) {
            console.log('SUCCESS !! '+data);
          });
        }
    };
    
    $scope.addBookToMyCollec = function(book) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/book/addtocollec.html',
            controller: 'BookCollecController',
            resolve: {
                book: function () {
                    return book;
                }
              }
          });
    };
    
} ]);

angular.module('medekApp.controllers').controller('BookCollecController',[
'$scope',
'$rootScope',
'$modalInstance',
'BookService',
'book',
function ($scope, $rootScope, $modalInstance, BookService, book) {
	$scope.book = book;
	$scope.mybook = {
			bookId: book.id,
			userId: $rootScope.user.id,
			rating: 0,
			comment: "",
			signed: false
	};

    $scope.ok = function () {
    	BookService.addBookToCollection($scope.mybook).then (
                function(response) {
                    alert('Add successful');
                    $modalInstance.close('success');
                }, function(reason) {
                    alert('Add failed');
                    $modalInstance.close('fail');
                });
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);