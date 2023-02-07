import React from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const withRouter = WrappedComponent => props => {
  const params = useParams();
  const navigate = useNavigate();
 
  return (
    <WrappedComponent
      {...props}
      params={params}
      navigate={navigate}
    />
  );
};
 
export default withRouter;