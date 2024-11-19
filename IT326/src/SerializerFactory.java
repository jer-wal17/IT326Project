/*-------------------------------------------------------------------*
 * Class Name: SerializerFactory                                     *
 *                                                                   *
 * Description: abstraction for a way to create serializer           *
 *              components such as connection serializers and        *
 *              query serializers                                    *
 *                                                                   *
 *-------------------------------------------------------------------*/
public abstract class SerializerFactory {

    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // creates a ConnectionSerializer object
    public abstract ConnectionSerializer createConnectionSerializer();

    // creates a QuerySerializer object
    public abstract QuerySerializer createQuerySerializer();

}