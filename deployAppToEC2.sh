sam build --template sam-template.yaml

sam package --template-file .aws-sam/build/template.yaml --output-template-file packaged.yaml --s3-bucket online-book-store-deployment-stack

sam deploy --template-file packaged.yaml --stack-name online-book-store